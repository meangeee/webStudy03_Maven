package kr.or.ddit.enums;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public enum FileCommand {
	COPY((srcFile, targetFolder)->{
		File targetFile = new File(targetFolder, srcFile.getName());
		Files.copy(Paths.get(srcFile.getPath()), Paths.get(targetFile.getPath()), StandardCopyOption.REPLACE_EXISTING);
	}), 
	MOVE((srcFile, targetFolder)->{
		File targetFile = new File(targetFolder, srcFile.getName());
		Files.move(Paths.get(srcFile.getPath()), Paths.get(targetFile.getPath()), StandardCopyOption.REPLACE_EXISTING);
	}), 
	DELETE((srcFile, targetFolder)->{
		Files.delete(Paths.get(srcFile.getPath()));
	});
	@FunctionalInterface
	public static interface CommandProcessor{
		public void process(File srcFile, File targetFolder) throws IOException;
	}
	
	private CommandProcessor realProcessor;
	private FileCommand(CommandProcessor realProcessor) {
		this.realProcessor = realProcessor;
	}

	public void process(File srcFile, File targetFolder) throws IOException {
		realProcessor.process(srcFile, targetFolder);
	}
}
