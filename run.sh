find . -name "*.java" > src.txt
javac -sourcepath @src.txt
java src.Console
