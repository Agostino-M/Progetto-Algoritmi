#include <stdio.h>
#include <stdlib.h>
#include "merge_binary_insertion_sort.h"
#include "unity.h"

#define ARRAY_INT_LENGHT 50

/*
 * Test suite for sorted array data structure and algorithms 
*/

// comparator ralation used in tests
static int comparator_int_asc(void *i1, void *i2)
{
    int *int1 = (int *)i1;
    int *int2 = (int *)i2;
    if ((*int1) == (*int2))
        return (0);

    else if ((*int1) > (*int2))
        return 1;

    else
        return -1;
}

// comparator ralation used in tests
static int comparator_int_desc(void *i1, void *i2)
{
    int *int1 = (int *)i1;
    int *int2 = (int *)i2;
    if ((*int1) == (*int2))
        return (0);

    else if ((*int1) < (*int2))
        return 1;

    else
        return -1;
}

// Data elements that are initialized before each test
static void **array_int;
static int i1, i2, i3;

void setUp(void)
{
    i1 = 5;
    i2 = 7;
    i3 = 1;
    array_int = malloc(ARRAY_INT_LENGHT * sizeof(int *));
}

void tearDown(void)
{
    sorted_array_free_memory();
}

static void test_sorted_array_three_el(void)
{
    int *array_expected[] = {&i3, &i1, &i2}; //1 5 7
    int *actual_array[] = {&i1, &i2, &i3}; //5 7 1


    sorted_array_sort(actual_array, comparator_int_asc, 0, 2);

    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, actual_array, 3);
}

int main(void){
    // test session
    UNITY_BEGIN();

    RUN_TEST(test_sorted_array_three_el);

    return UNITY_END();
}