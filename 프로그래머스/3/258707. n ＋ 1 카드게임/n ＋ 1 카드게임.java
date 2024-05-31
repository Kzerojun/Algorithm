import java.util.*;

class Solution {
    static List<Integer> deck;
    static List<Integer> candidates;
    static int N;
    
    public int solution(int coin, int[] cards) {
        init(cards);
        return playGame(coin, cards);
    }

    private static void init(int[] cards) {
        deck = new ArrayList<>();
        candidates = new ArrayList<>();
        N = cards.length;

        for (int i = 0; i < N / 3; i++) {
            deck.add(cards[i]);
        }
    }

    private static int playGame(int coin, int[] cards) {
        int curIdx = N / 3;
        int round = 1;

        while (true) {
            // 카드 뭉치에 남은 카드가 없는 경우
            if (curIdx >= N) {
                break;
            }

            // 두 장 뽑기
            candidates.add(cards[curIdx++]);
            candidates.add(cards[curIdx++]);

            // 우선순위 0 -> 만약 카드를 뽑지 않고 라운드 진행 가능한지
            if (canRound(deck)) {
                round++;
                continue;
            }

            // 우선순위 1 -> 카드 한 장 뽑기
            if (coin >= 1) {
                boolean found = false;
                for (int i = 0; i < deck.size(); i++) {
                    int target = N + 1 - deck.get(i);

                    if (candidates.contains(target)) {
                        deck.remove(i);
                        candidates.remove((Integer) target);
                        coin--;
                        round++;
                        found = true;
                        break;
                    }
                }
                if (found) continue;
            }

            // 우선순위 2 -> 카드 두 장 뽑기
            if (coin >= 2) {
                if (canRound(candidates)) {
                    coin -= 2;
                    round++;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        
        return round;
    }

    private static boolean canRound(List<Integer> list) {
       
        if(list.isEmpty()) {
            return false;
        }
        
         Collections.sort(list);
        
        for (int i = 0; i < list.size(); i++) {
            int target = N + 1 - list.get(i);

           
            
            int left = 0; // 현재 i를 제외한 나머지 리스트에서 탐색
            int right = list.size() - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (list.get(mid) > target) {
                    right = mid - 1;
                } else if (list.get(mid) < target) {
                    left = mid + 1;
                } else {
                    list.remove(mid); // 이미 i를 제거했으므로 인덱스 조정
                    list.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
}
