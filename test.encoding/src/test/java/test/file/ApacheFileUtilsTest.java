<<<<<<< HEAD
package test.file;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;


public class ApacheFileUtilsTest {
	private static final String MAIN_PATH = "C:\\workspace\\tempFile\\";
	
	/**
	 * 파일쓰기 테스트
	 * @throws IOException
	 */
	@Test
	public void writeTest() throws IOException{
		// generate file
		File tempFile1 = FileUtils.getFile(MAIN_PATH+"tempFile1.txt");
		
		// write file
		String testSentence = "test write";
		FileUtils.write(tempFile1, testSentence);
		
		// test
		String content = FileUtils.readFileToString(tempFile1);
		assertEquals(testSentence, content);
		
		// compare between file
		File tempFile2 = FileUtils.getFile(MAIN_PATH+"tempFile2.txt");
		FileUtils.write(tempFile2, testSentence);
		
		assertTrue(FileUtils.contentEquals(tempFile1, tempFile2));
	}
	
	@Test
	public void appendTest() throws IOException{
		// generate file
		File appendFile = FileUtils.getFile(MAIN_PATH+"appendTestFile.txt");
		
		// write file
		String sentence = "sentence1";
		FileUtils.write(appendFile, sentence);
		
		// append string
		String appendSentence = "append text";
		FileUtils.writeStringToFile(appendFile, appendSentence, true);
		String content = FileUtils.readFileToString(appendFile);
		assertEquals(sentence+appendSentence, content);
	}
}
=======
package test.file;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;


public class ApacheFileUtilsTest {
	private static final String MAIN_PATH = "C:\\workspace\\tempFile\\";
	
	/**
	 * 파일쓰기 테스트
	 * @throws IOException
	 */
	@Test
	public void writeTest() throws IOException{
		// generate file
		File tempFile1 = FileUtils.getFile(MAIN_PATH+"tempFile1.txt");
		
		// write file
		String testSentence = "test write";
		FileUtils.write(tempFile1, testSentence);
		
		// test
		String content = FileUtils.readFileToString(tempFile1);
		assertEquals(testSentence, content);
		
		// compare between file
		File tempFile2 = FileUtils.getFile(MAIN_PATH+"tempFile2.txt");
		FileUtils.write(tempFile2, testSentence);
		
		assertTrue(FileUtils.contentEquals(tempFile1, tempFile2));
	}
}
>>>>>>> branch 'master' of https://github.com/hjs4827/myExam.git
