package kr.or.ddit.enums;

import java.io.File;
import java.io.IOException;

@FunctionalInterface
public interface CommandProcessor {
   void process(File var1, File var2) throws IOException;
}