package seedu.address.logic.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMMAND_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALIAS_ADD;

import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.List;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Parser parser, String userInput, Command expectedCommand) {
        try {
            Command command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(Parser parser, String userInput, String expectedMessage) {
        try {
            parser.parse(userInput);
            fail("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }

    /**
     * Asserts that the parsing of alias word if is command wordis unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseAliasWord(Parser parser, List<String> commands) {
        //testing alias that it is not a command word
        String message = String.format(AliasCommand.MESSAGE_INVALID_ALIAS,
                AliasCommand.MESSAGE_INVALID_ALIAS_DESCRIPTION);
        int length = commands.size();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                String command = commands.get(i) + " " + commands.get(j);
                assertParseFailure(parser, command, message);
            }
        }
    }
}
