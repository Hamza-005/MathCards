import java.util.Random;

/**
 * Question class that contains the question and answer.
 */
public class Question {
    private final int firstNumber;
    private final int secondNumber;
    private final char operator;
    private int answer;
    private static final Random random = new Random();

    public Question(int firstNumber, int secondNumber, char operator) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;

        //after recieving first, second, and operator, calculate answer

        switch (operator) {
            case '+':
                this.answer = firstNumber + secondNumber;
                break;
            case '-':
                this.answer = firstNumber - secondNumber;
                break;
            case '*':
                this.answer = firstNumber * secondNumber;
                break;
            case '/':
                this.answer = firstNumber / secondNumber;
                break;
        }
    }

    /**
     * A static function that generate question based on the factor and types.
     * if factor is 'R', then the question will be random.
     * if factor is a number, then the question will be based on the factor.
     * @param factor 'R' or a number
     * @param types +, -, *, /
     * @return a question
     */
    public static Question generateQuestion(char factor, char... types) {
        int firstNumber;
        int secondNumber;
        char operator = ' ';

        //if factor random
        if (factor == 'R') {
            //then create one of the number in range 0-10 and the other in range 0-100
            int i = random.nextInt(2);
            //if i == 0 then firstNumber is in range 0-10 and secondNumber is in range 0-100
            //if i == 1 then firstNumber is in range 0-100 and secondNumber is in range 0-10
            if (i == 0) {
                firstNumber = random.nextInt(10);
                secondNumber = random.nextInt( 100);
            } else {
                firstNumber = random.nextInt( 100);
                secondNumber = random.nextInt( 10);
            }
        } else {
            //if factor is a number, then create one of the number based on the factor and the other in range 0-100
            int i = random.nextInt(2);
            //if i == 0 then firstNumber is factor and secondNumber is in range 0-100
            //if i == 1 then firstNumber is in range 0-100 and secondNumber is factor
            if (i == 0) {
                firstNumber = Character.getNumericValue(factor);
                secondNumber = random.nextInt( 100);
            } else {
                firstNumber = random.nextInt( 100);
                secondNumber = Character.getNumericValue(factor);
            }
        }

        //if types is empty, then randomly choose one of the operator
        if (types.length == 0) {
            //randomly choose one of the operator
            int i = random.nextInt(4);
            switch (i) {
                case 0:
                    operator = '+';
                    break;
                case 1:
                    operator = '-';
                    break;
                case 2:
                    operator = '*';
                    break;
                case 3:
                    operator = '/';
                    break;
            }
        } else {
            //if types is not empty, then randomly choose one of the operator in types
            int i = random.nextInt(types.length);
            //i is the index of the operator in types
            operator = types[i];
        }

        //return a question
        return new Question(firstNumber, secondNumber, operator);
    }

    public int getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return firstNumber + " " + operator + " " + secondNumber + " = ??";
    }
}
