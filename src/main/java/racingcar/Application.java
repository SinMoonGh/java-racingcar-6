package racingcar;
import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static class RaceGame {

        // 자동차의 전진을 결정하는 함수
        public void moveCars(String[] carNames, Map<String, String> carData) {
            for (String carName : carNames) {
                // 무작위 수 구하기
                int randomNumber = Randoms.pickNumberInRange(0, 9);

                if (randomNumber >= 4) {
                    String currentDistance = carData.get(carName);
                    carData.put(carName, currentDistance + "-");
                }
            }
        }

        // 레이싱 결과를 출력하는 함수
        public void printRaceResult(String[] carNames, Map<String, String> carData) {
            for (String carName : carNames) {
                System.out.println(carName + " : " + carData.get(carName));
            }
            System.out.println();
        }

        // 경주할 자동차 이름 입력하기
        public String[] inputCarName() {
            System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
            String inputCarName = Console.readLine();

            // 콤마(,)로 문자열을 분리하여 배열에 저장
            String[] carNames = inputCarName.split(",");

            // 각 자동차 이름이 5자 초과일 경우 예외 발생
            for (String carName : carNames) {
                if (carName.trim().length() > 5) {  // trim()은 앞뒤 공백 제거
                    throw new IllegalArgumentException("자동차 이름은 5자 이하만 가능합니다: " + carName);
                }
            }

            return carNames;
        }

        // 경주 횟수 입력하기
        public String inputRaceCount(){
            System.out.println("시도할 회수는 몇회인가요?");
            String tryCarGame = Console.readLine();

            // 입력 값 검증: 숫자가 아닌 값을 입력했거나 빈 값을 입력했을 때 예외 발생
            if (!tryCarGame.matches("\\d+")) {
                throw new IllegalArgumentException("잘못된 입력입니다. 숫자를 입력해주세요.");
            }

            return tryCarGame;
        }

        // 최대 이동거리 구하기
        public int getMaxDistance(Map<String, String> carData, String[] carNames) {
            int maxDistance = 0;
            for (String carName : carNames) {
                int currentDistance = carData.get(carName).length();
                if (currentDistance > maxDistance) {
                    maxDistance = currentDistance;
                }
            }
            return maxDistance;
        }

        // 우승자 구하기
        public List<String> getWinners(Map<String, String> carData, String[] carNames, int maxDistance) {
            List<String> winners = new ArrayList<>();
            for (String carName : carNames) {
                int currentDistance = carData.get(carName).length();
                if (currentDistance == maxDistance) {
                    winners.add(carName);  // 우승자 추가
                }
            }
            return winners;
        }

        // 우승자를 출력하기
        public void printWinners(List<String> winners) {
            System.out.println("최종 우승자 : " + String.join(", ", winners));
        }
    }
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        RaceGame raceGame = new RaceGame();

        String[] carNames = raceGame.inputCarName();

        String tryCarGame = raceGame.inputRaceCount();

        // 자동차 이름을 키로 하고, 자동차의 이동 거리를 저장하는 맵
        Map<String, String> carData = new HashMap<>();

        // 자동차 이름을 맵에 초기화 (처음엔 빈 문자열로 시작)
        for (String carName : carNames) {
            carData.put(carName, "");
        }

        // 레이스마다 자동차 전진 거리 HashMap에 저장
        System.out.println("실행결과");
        for(int i=0; i<Integer.parseInt(tryCarGame); i++){
            raceGame.moveCars(carNames, carData);
            raceGame.printRaceResult(carNames, carData);
        }

        int maxDistance = raceGame.getMaxDistance(carData, carNames); // 최장 거리를 구하는 함수
        List<String> winners = raceGame.getWinners(carData, carNames, maxDistance);
        raceGame.printWinners(winners);
    }
}
