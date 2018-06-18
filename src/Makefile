BUILD_NR := $(shell cat BUILD)
BUILD_NR_MINUS_1 := $(shell cat BUILD_MINUS_1)
; COMPILE_OPTIONS := -d ../class

all1: GUI.class StaticVariables.class MacierzSasiedztwa.class MapaOBSOLETE.class ObrazekKoncaGry.class Pole.class \
PoleMacierzySasiedztwa.class Statek.class Main.class
	echo Sprawdzam czy jest cos do roboty...
	make build

GUI.class: GUI.java
	javac $(COMPILE_OPTIONS) GUI.java

StaticVariables.class: StaticVariables.java
	javac $(COMPILE_OPTIONS) StaticVariables.java

MacierzSasiedztwa.class: MacierzSasiedztwa.java
	javac $(COMPILE_OPTIONS) MacierzSasiedztwa.java

MapaOBSOLETE.class: MapaOBSOLETE.java
	javac $(COMPILE_OPTIONS) MapaOBSOLETE.java

ObrazekKoncaGry.class: ObrazekKoncaGry.java
	javac $(COMPILE_OPTIONS) ObrazekKoncaGry.java

Pole.class: Pole.java
	javac $(COMPILE_OPTIONS) Pole.java

PoleMacierzySasiedztwa.class: PoleMacierzySasiedztwa.java
	javac $(COMPILE_OPTIONS) PoleMacierzySasiedztwa.java

Statek.class: Statek.java
	javac $(COMPILE_OPTIONS) Statek.java

Main.class: Main.java
	javac $(COMPILE_OPTIONS) Main.java


archive:
	mkdir backup/build-$(BUILD_NR)
	cp *.java Makefile *.jpg *.txt TODO *.bash BUILD backup/build-$(BUILD_NR)
	tar czf backup/build-$(BUILD_NR)-full-archive.tgz backup/build-$(BUILD_NR)/*
	rm backup/build-$(BUILD_NR)/*
	rmdir backup/build-$(BUILD_NR)



test: Statek.class MapaOBSOLETE.class Pole.class
	bash -c 'time javac $(COMPILE_OPTIONS) PoleTest.java StatekTest.java MapaOBSOLETETest.java GUITest.java'
	java PoleTest
	java StatekTest
	java MapaOBSOLETETest
	java GUITest

mapaOBSOLETE:	MapaOBSOLETE.java
	bash -c 'time javac $(COMPILE_OPTIONS) MapaOBSOLETE.java'
	java MapaGUI
all: 
	bash -c 'time javac $(COMPILE_OPTIONS) *.java'

build:
	mkdir backup/build-$(BUILD_NR)
	cp *.java backup/build-$(BUILD_NR)
	cp Makefile backup/build-$(BUILD_NR)
	tar czf backup/build-$(BUILD_NR).tgz backup/build-$(BUILD_NR)/*
	rm backup/build-$(BUILD_NR)/*
	rmdir backup/build-$(BUILD_NR)
	bash inc_build_nr.bash

run:	Main.class
	java Main


clean:
	rm *.class

remake:
	make clean
	make

restore:
	bash dec_build_nr.bash
	echo $(BUILD_NR_MINUS_1)	
	tar xvzf backup/build-$(BUILD_NR_MINUS_1).tgz 
