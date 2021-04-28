#include <stdio.h>
#include <stdlib.h>
#include <string.h>
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

static int comparator_string(void *s1, void *s2)
{
    char *string1 = (char *)s1;
    char *string2 = (char *)s2;
    return strcmp(string1, string2);
}

// Data elements that are initialized before each test
static int i1, i2, i3, i4, i5, i6, i7, i8, i9, i10;

void setUp(void)
{
    i1 = 1;
    i2 = 2;
    i3 = 3;
    i4 = 4;
    i5 = 5;
    i6 = 6;
    i7 = 7;
    i8 = 8;
    i9 = 9;
    i10 = 10;
}

void tearDown(void)
{
}

static void test_three_el_int_asc(void)
{
    int *array_expected[] = {&i1, &i2, &i3};
    int *actual_array[] = {&i3, &i2, &i1};

    sorted_array_sort((void **)actual_array, comparator_int_asc, 3);
    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, actual_array, 3);
}

static void test_three_el_int_desc(void)
{
    int *array_expected[] = {&i3, &i2, &i1};
    int *actual_array[] = {&i1, &i2, &i3};

    sorted_array_sort((void **)actual_array, comparator_int_desc, 3);
    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, actual_array, 3);
}

static void test_one_el_int(void)
{
    int *array_expected[] = {&i1};
    int *actual_array[] = {&i1};

    sorted_array_sort((void **)actual_array, comparator_int_asc, 1);
    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, actual_array, 1);
}

static void test_equal_el(void)
{
    int *array_expected[] = {&i1, &i1, &i1};
    int *actual_array[] = {&i1, &i1, &i1};

    sorted_array_sort((void **)actual_array, comparator_int_asc, 3);
    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, actual_array, 3);
}

static void test_ten_el_int_asc(void)
{
    int *array_expected[] = {&i1, &i2, &i3, &i4, &i5, &i6, &i7, &i8, &i9, &i10};
    int *actual_array[] = {&i5, &i2, &i4, &i7, &i10, &i8, &i6, &i1, &i3, &i9};

    sorted_array_sort((void **)actual_array, comparator_int_asc, 10);
    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, actual_array, 10);
}

static void test_three_el_string_asc(void)
{
    char *array_expected[] = {"albero", "bici", "casa"};
    char *actual_array[] = {"casa", "bici", "albero"};

    sorted_array_sort((void **)actual_array, comparator_string, 3);
    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, actual_array, 3);
}

static void test_one_el_string(void)
{
    char *array_expected[] = {"albero"};
    char *actual_array[] = {"albero"};

    sorted_array_sort((void **)actual_array, comparator_string, 1);
    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, actual_array, 1);
}

int main(void)
{
    // test session
    UNITY_BEGIN();

    RUN_TEST(test_three_el_int_asc);
    RUN_TEST(test_three_el_int_desc);
    RUN_TEST(test_one_el_int);
    RUN_TEST(test_equal_el);
    RUN_TEST(test_ten_el_int_asc);
    RUN_TEST(test_three_el_string_asc);
    RUN_TEST(test_one_el_string);

    return UNITY_END();
}