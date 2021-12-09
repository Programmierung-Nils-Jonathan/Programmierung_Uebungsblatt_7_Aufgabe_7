MAINCLASS = Main

.DEFAULT_GOAL := compile-run

compile-run:
	@make compile
	@make run

compile:
	javac ${MAINCLASS}.java

run:
	java ${MAINCLASS} testRepository

clean:
	@rm ./*.class || true

doc:
	javadoc -d doc ${MAINCLASS}.java