#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "merge_binary_insertion_sort.h"

#define SIZE 10

struct record
{
    char *string_field;
    int integer_field;
};

struct record **records;
int cont = 0;

// It takes as input two pointers to struct record
// It returns 1 if and only if the integer field of the first record is less than
// the integer field of the second one (0 otherwise)

static int comparator_record_int_field(void *r1_p, void *r2_p)
{
    if (r1_p == NULL)
    {
        fprintf(stderr, "precedes_record_int_field: the first parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }
    if (r2_p == NULL)
    {
        fprintf(stderr, "precedes_record_int_field: the second parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }

    struct record *rec1_p = (struct record *)r1_p;
    struct record *rec2_p = (struct record *)r2_p;

    if (rec1_p->integer_field == rec2_p->integer_field)
        return 0;

    else if (rec1_p->integer_field > rec2_p->integer_field)
        return 1;

    else
        return -1;
}

// It takes as input two pointers to struct record
// It returns 1 if and only if the string field of the first record is less than
// the string field of the second one (0 otherwise)
static int comparator_record_string_field(void *r1_p, void *r2_p)
{
    if (r1_p == NULL)
    {
        fprintf(stderr, "precedes_string: the first parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }
    if (r2_p == NULL)
    {
        fprintf(stderr, "precedes_string: the second parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }
    struct record *rec1_p = (struct record *)r1_p;
    struct record *rec2_p = (struct record *)r2_p;

    return strcmp(rec1_p->string_field, rec2_p->string_field);
}

static void load_array(const char *file_path)
{
    records = malloc(SIZE * sizeof(struct record));
    FILE *fp;
    char *read_line_p;
    int buf_size = 1024;
    char buffer[1024];

    fp = fopen(file_path, "r");
    if (fp == NULL)
    {
        fprintf(stderr, "Error reading file\n");
        exit(EXIT_FAILURE);
    }

    while (fgets(buffer, buf_size, fp) != NULL && cont < SIZE)
    {
        read_line_p = malloc((strlen(buffer) + 1) * sizeof(char));
        if (read_line_p == NULL)
        {
            fprintf(stderr, "main: unable to allocate memory for the read line");
            exit(EXIT_FAILURE);
        }
        strcpy(read_line_p, buffer);
        strtok(read_line_p, ",");
        char *string_field_in_read_line_p = strtok(NULL, ",");
        char *integer_field_in_read_line_p = strtok(NULL, ",");
        char *string_field = malloc((strlen(string_field_in_read_line_p) + 1) * sizeof(char));
        if (string_field == NULL)
        {
            fprintf(stderr, "main: unable to allocate memory for the string field of the read record");
            exit(EXIT_FAILURE);
        }
        strcpy(string_field, string_field_in_read_line_p);
        int integer_field = atoi(integer_field_in_read_line_p);
        struct record *record_p = malloc(sizeof(struct record));
        record_p->string_field = string_field;
        record_p->integer_field = integer_field;
        records[cont] = record_p;
        cont++;
        free(read_line_p);
    }
}

static void test_with_comparison_function(const char *file_path)
{
    load_array(file_path);

    /*
    for (int i = 0; i < SIZE; i++)
    {
        printf("<%s,%d>\n", records[i]->string_field, records[i]->integer_field);
    }
    */

    printf("\n\nOrdino per il  campo intero...\n\n");

    sorted_array_sort((void **)records, comparator_record_int_field, SIZE);

    /*
    for (int i = 0; i < SIZE; i++)
    {
        printf("<%s,%d>\n", records[i]->string_field, records[i]->integer_field);
    }
    */

    printf("\n\nDONE");

    //sorted_array_sort((void **)records, comparator_record_string_field, SIZE);

    //printf("\n\n");

    /*for (int i = 0; i < SIZE; i++)
    {
        printf("<%s,%d>\n", records[i]->string_field, records[i]->integer_field);
    }
    */

    //printf("\n\n");

    free(records);
}

// It should be invoked with one parameter specifying the path of the data file
int main(int argc, char const *argv[])
{
    if (argc < 2)
    {
        printf("Usage: merge_binary_insertion_sort <path_to_data_file>\n");
        exit(EXIT_FAILURE);
    }
    test_with_comparison_function(argv[1]);

    return (EXIT_SUCCESS);
}
