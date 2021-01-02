package GuessGame.Data.TestServiceLayer;

import GuessGame.Service.ServiceLayer;
import GuessGame.TestApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ServiceLayerTest {

    @Autowired
    ServiceLayer serviceLayer;

    @Test
    public void testDetermineResult1() { //Test for the case of having only 2 partial
        String guess = "1234";
        String answer = "2156";
        String result = serviceLayer.determineResult(guess, answer);

        assertEquals("e:0:p:2", result);
    }

    @Test
    public void testDetermineResult2() {//Test for the case of having 4 exacts therefore the correct guess
        String guess = "1234";
        String answer = "1234";
        String result = serviceLayer.determineResult(guess, answer);

        assertEquals("e:4:p:0", result);
    }

    @Test
    public void testDetermineResult3(){//Test for the case of having a completely wrong guess
        String guess = "1234";
        String answer = "5678";
        String result = serviceLayer.determineResult(guess, answer);

        assertEquals("e:0:p:0", result);
    }

    @Test
    public void testDetermineResult4(){//Test for the case of having 4 partials
        String guess = "1234";
        String answer = "4321";
        String result = serviceLayer.determineResult(guess, answer);

        assertEquals("e:0:p:4", result);
    }

    @Test
    public void testDetermineResult5(){//Test for the case of having 2 exacts and 2 partials
        String guess = "1234";
        String answer = "1243";
        String result = serviceLayer.determineResult(guess, answer);

        assertEquals("e:2:p:2", result);
    }
    @Test
    public void testDetermineResult6(){//Test for the case of having 2 exacts
        String guess = "1234";
        String answer = "1267";
        String result = serviceLayer.determineResult(guess, answer);

        assertEquals("e:2:p:0", result);
    }

    @Test
    public void testGenerateAnswer(){//Test to make sure only non-repeating numbers are produced
        String answer = serviceLayer.generateAnswer();

        boolean check = false;

        for (int i = 0; i < answer.length(); i++){
            char s = answer.charAt(i);
            for(int j = i+1; j < answer.length(); j++){
                char l = answer.charAt(j);
                if(s == l){ //check if their are any repeating digits
                    check = true;
                }
            }
        }

        assertEquals(false, check);
    }

}
