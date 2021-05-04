#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "edit_distance.h"
#include "unity.h"

static void test1(void)
{
    char s1[] = "casa";
    char s2[] = "cassa";
    int m = (int)strlen(s1);
    int n = (int)strlen(s2);

    TEST_ASSERT_EQUAL_INT(1, edit_distance(s1, s2, m, n));
}

static void test1_dyn(void)
{
    char s1[] = "casa";
    char s2[] = "cassa";
    int m = (int)strlen(s1);
    int n = (int)strlen(s2);

    TEST_ASSERT_EQUAL_INT(1, edit_distance_dyn(s1, s2, m, n));
}

static void test2(void)
{
    char s1[] = "casa";
    char s2[] = "cara";
    int m = (int)strlen(s1);
    int n = (int)strlen(s2);

    TEST_ASSERT_EQUAL_INT(2, edit_distance(s1, s2, m, n));
}

static void test2_dyn(void)
{
    char s1[] = "casa";
    char s2[] = "cara";
    int m = (int)strlen(s1);
    int n = (int)strlen(s2);

    TEST_ASSERT_EQUAL_INT(2, edit_distance_dyn(s1, s2, m, n));
}

static void test3(void)
{
    char s1[] = "tassa";
    char s2[] = "passato";
    int m = (int)strlen(s1);
    int n = (int)strlen(s2);

    TEST_ASSERT_EQUAL_INT(4, edit_distance(s1, s2, m, n));
}

static void test3_dyn(void)
{
    char s1[] = "tassa";
    char s2[] = "passato";
    int m = (int)strlen(s1);
    int n = (int)strlen(s2);

    TEST_ASSERT_EQUAL_INT(4, edit_distance_dyn(s1, s2, m, n));
}

static void test4(void)
{
    char s1[] = "pioppo";
    char s2[] = "pioppo";
    int m = (int)strlen(s1);
    int n = (int)strlen(s2);

    TEST_ASSERT_EQUAL_INT(0, edit_distance(s1, s2, m, n));
}

static void test4_dyn(void)
{
    char s1[] = "pioppo";
    char s2[] = "pioppo";
    int m = (int)strlen(s1);
    int n = (int)strlen(s2);

    TEST_ASSERT_EQUAL_INT(0, edit_distance_dyn(s1, s2, m, n));
}

static void test5(void)
{
    char s1[] = "";
    char s2[] = "";
    int m = (int)strlen(s1);
    int n = (int)strlen(s2);

    TEST_ASSERT_EQUAL_INT(0, edit_distance(s1, s2, m, n));
}

static void test5_dyn(void)
{
    char s1[] = "";
    char s2[] = "";
    int m = (int)strlen(s1);
    int n = (int)strlen(s2);

    TEST_ASSERT_EQUAL_INT(0, edit_distance_dyn(s1, s2, m, n));
}

static void test6(void)
{
    char s1[] = "";
    char s2[] = "ciaocomestai";
    int m = (int)strlen(s1);
    int n = (int)strlen(s2);

    TEST_ASSERT_EQUAL_INT(n, edit_distance(s1, s2, m, n));
}

static void test6_dyn(void)
{
    char s1[] = "";
    char s2[] = "ciaocomestai";
    int m = (int)strlen(s1);
    int n = (int)strlen(s2);

    TEST_ASSERT_EQUAL_INT(n, edit_distance_dyn(s1, s2, m, n));
}

int main(void)
{
    // test session
    UNITY_BEGIN();

    RUN_TEST(test1);
    RUN_TEST(test1_dyn);
    RUN_TEST(test2);
    RUN_TEST(test2_dyn);
    RUN_TEST(test3);
    RUN_TEST(test3_dyn);
    RUN_TEST(test4);
    RUN_TEST(test4_dyn);
    RUN_TEST(test5);
    RUN_TEST(test5_dyn);
    RUN_TEST(test6);
    RUN_TEST(test6_dyn);

    return UNITY_END();
}
