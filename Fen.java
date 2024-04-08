
import java.util.HashMap;
import java.util.Map;
public class Fen {
    
    public static void load(String fen, Board b) {
        int rank = 0;   // Rank or row of the board
        int square = 0; // Square in 'rank'
        char query;     // Temp holder for current char

        // begin hashmap solution
        Character[][] keysVals = new Character[][] {
                {'p', '\u265f'}, {'P', '\u2659'}, {'r', '\u265c'}, {'R', '\u2656'}, {'n', '\u265e'}, {'N', '\u2658'},
                {'b', '\u265d'}, {'B', '\u2657'}, {'q', '\u265b'}, {'Q', '\u2655'}, {'k', '\u265a'}, {'K', '\u2654'}
        };

        Map<Character, Character> map = new HashMap<>();

        for (Character[] keyVal: keysVals) {
            map.put(keyVal[0], keyVal[1]);
        }

        // Iterate over FEN code chars, updating Board object accordingly
        for(int i = 0; i < fen.length(); i++) {
            query = fen.charAt(i);

            if(query == '/') { 
                rank++;
                square = 0;
            } else if (Character.isDigit(query)) { 
                square += Character.getNumericValue(query);
            } else { 
                b.setPiece(rank, square, new Piece(map.get(query), rank, square++, !Character.isUpperCase(query)));
            }
        }
    }
}
