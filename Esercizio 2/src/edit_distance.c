#include "edit_distance.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

int min(int a, int b, int c);
int edit_distance_dyn_impl(char *s1, char *s2, int m, int n, int mat[][MAX_COL]);
int edit_distance_impl(char *s1, char *s2, int m, int n);

int min(int a, int b, int c)
{
    int min_temp = MIN(a, b);
    return MIN(min_temp, c);
}

int edit_distance(char *s1, char *s2, int m, int n)
{
    if (s1 == NULL)
    {
        fprintf(stderr, "edit_distance_dyn: the first parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }
    if (s2 == NULL)
    {
        fprintf(stderr, "edit_distance_dyn: the second parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }
    if (m < 0)
    {
        fprintf(stderr, "edit_distance_dyn: m length cannot be negative");
        exit(EXIT_FAILURE);
    }
    if (n < 0)
    {
        fprintf(stderr, "edit_distance_dyn: n length cannot be negative");
        exit(EXIT_FAILURE);
    }

    return edit_distance_impl(s1, s2, m, n);
}

int edit_distance_impl(char *s1, char *s2, int m, int n)
{
    if (m == 0)
        return n;

    if (n == 0)
        return m;

    int d_no_op;
    int d_canc;
    int d_ins;

    if (s1[0] == s2[0])
        d_no_op = edit_distance(s1 + 1, s2 + 1, m - 1, n - 1);
    else
        d_no_op = INT_MAX;

    d_canc = 1 + edit_distance(s1, s2 + 1, m, n - 1);
    d_ins = 1 + edit_distance(s1 + 1, s2, m - 1, n);

    return min(d_no_op, d_canc, d_ins);
}

int edit_distance_dyn(char *s1, char *s2, int m, int n)
{
    if (s1 == NULL)
    {
        fprintf(stderr, "edit_distance_dyn: the first parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }
    if (s2 == NULL)
    {
        fprintf(stderr, "edit_distance_dyn: the second parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }
    if (m < 0)
    {
        fprintf(stderr, "edit_distance_dyn: m length cannot be negative");
        exit(EXIT_FAILURE);
    }
    if (n < 0)
    {
        fprintf(stderr, "edit_distance_dyn: n length cannot be negative");
        exit(EXIT_FAILURE);
    }

    int mat[m][MAX_COL];
    memset(mat, -1, sizeof(mat));
    return edit_distance_dyn_impl(s1, s2, m, n, mat);
}

int edit_distance_dyn_impl(char *s1, char *s2, int m, int n, int mat[][MAX_COL])
{
    if (m == 0)
        return n;

    if (n == 0)
        return m;

    if (mat[m - 1][n - 1] != -1)
        return mat[m - 1][n - 1];

    if (s1[m - 1] == s2[n - 1])
        return mat[m - 1][n - 1] = edit_distance_dyn_impl(s1, s2, m - 1, n - 1, mat);

    return mat[m - 1][n - 1] = 1 + min(edit_distance_dyn_impl(s1, s2, m, n - 1, mat),        // Insert
                                       edit_distance_dyn_impl(s1, s2, m - 1, n, mat),        // Remove
                                       1 + edit_distance_dyn_impl(s1, s2, m - 1, n - 1, mat) // Replace
                                   );
}