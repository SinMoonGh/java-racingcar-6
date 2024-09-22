package study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import racingcar.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringTest {

    Application.RaceGame raceGame = new Application.RaceGame();

    @Test
    void split_메서드로_주어진_값을_구분() {
        String input = "1,2";
        String[] result = input.split(",");

        assertThat(result).contains("2", "1");
        assertThat(result).containsExactly("1", "2");
    }

    @Test
    void split_메서드_사용시_구분자가_포함되지_않은_경우_값을_그대로_반환() {
        String input = "1";
        String[] result = input.split(",");

        assertThat(result).contains("1");
    }

    @Test
    void substring_메서드로_특정_구간_값을_반환() {
        String input = "(1,2)";
        String result = input.substring(1, 4);

        assertThat(result).isEqualTo("1,2");
    }

    @Test
    void charAt_메서드로_특정_위치의_문자_찾기() {
        String input = "abc";
        char charAtElement = input.charAt(0);
        assertThat(charAtElement).isEqualTo('a');
    }

    @Test
    void charAt_메서드_사용시_문자열의_길이보다_큰_숫자_위치의_문자를_찾을_때_예외_발생() {
        String input = "abc";

        assertThatThrownBy(() -> input.charAt(5))
                .isInstanceOf(StringIndexOutOfBoundsException.class)
                .hasMessageContaining("String index out of range: 5");
    }

    @Test
    void getMaxDistance_최대_거리_구하기() {
        // given (테스트에 필요한 데이터 설정)
        Map<String, String> carData = Map.of("pobi", "--", "woni", "---", "jun", "--");
        String[] carNames = { "pobi", "woni", "jun" };

        // when (테스트하려는 함수 호출)
        int maxDistance = raceGame.getMaxDistance(carData, carNames);

        // then (결과 검증)
        assertThat(maxDistance).isEqualTo(3); // 최대 이동 거리는 "woni"의 3이 되어야 함
    }

    @Test
    void getWinners_우승자_찾기() {
        // given
        Map<String, String> carData = Map.of("pobi", "--", "woni", "---", "jun", "--");
        String[] carNames = { "pobi", "woni", "jun" };

        // when
        int maxDistance = raceGame.getMaxDistance(carData, carNames);
        List<String> winners = raceGame.getWinners(carData, carNames, maxDistance);

        // then
        assertThat(winners).containsExactly("woni");  // "woni"가 우승자로 포함되어 있어야 함
    }

    @Test
    void getWinners_동점_우승자_테스트() {
        // given (동점 상황 설정)
        Map<String, String> carData = Map.of("pobi", "---", "woni", "---", "jun", "--");
        String[] carNames = { "pobi", "woni", "jun" };

        // when
        int maxDistance = raceGame.getMaxDistance(carData, carNames);
        List<String> winners = raceGame.getWinners(carData, carNames, maxDistance);

        // then (pobi와 woni가 동점으로 우승)
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni");
    }

}
