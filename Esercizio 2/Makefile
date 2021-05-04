
all: bin/edit_distance bin/edit_distance_tests

clean:
	rm -f build/* bin/*

tests: bin/edit_distance_tests
	./bin/edit_distance_tests

usage: bin/edit_distance
	./bin/edit_distance correctme.txt dictionary.txt

CFLAGS = -g -Wall -Wextra -Wpedantic -Wconversion

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

bin/edit_distance: build/edit_distance.o build/edit_distance_main.o $(COMMON_DEPS)
	$(CC) -o bin/edit_distance build/edit_distance.o build/edit_distance_main.o

bin/edit_distance_tests: build/edit_distance_tests.o build/edit_distance.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/edit_distance_tests  build/edit_distance_tests.o build/edit_distance.o build/unity.o
