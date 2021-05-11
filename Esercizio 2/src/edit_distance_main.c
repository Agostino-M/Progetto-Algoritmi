#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <limits.h>
#include "edit_distance.h"
#define MAX_LENGHT 200

static void start_test(const char *file_path1, const char *file_path2)
{
    char buffer[1024];
    char buffer2[30];
    char **min_distance_words = malloc(MAX_LENGHT * sizeof(char *));
    int buf_size_correctme = 1024;
    int buf_size_dictionary = 30;
    int min = INT_MAX;
    int temp = 0;
    int cont = 0;
    char *read_line_p;
    char *dictionary_word;
    FILE *fp1;
    FILE *fp2;

    fp1 = fopen(file_path1, "r");
    if (fp1 == NULL)
    {
        fprintf(stderr, "Error reading file correct_me\n");
        exit(EXIT_FAILURE);
    }

    fp2 = fopen(file_path2, "r");
    if (fp2 == NULL)
    {
        fprintf(stderr, "Error reading file dictionary\n");
        exit(EXIT_FAILURE);
    }

    fgets(buffer, buf_size_correctme, fp1);
    read_line_p = malloc((strlen(buffer) + 1) * sizeof(char));
    if (read_line_p == NULL)
    {
        fprintf(stderr, "main: unable to allocate memory for the read line");
        exit(EXIT_FAILURE);
    }

    strcpy(read_line_p, buffer);
    char *correct_me_word;
    correct_me_word = strtok(read_line_p, ".,: ");

    while (correct_me_word != NULL)
    {
        //printf("\n Parola letta : %s \n", correct_me_word);

        // Cerco in dictionary le parole con edit ditsance mininimo

        min = INT_MAX;

        //Azzero il puntatore fp2
        rewind(fp2);

        while (fscanf(fp2, "%s", buffer2) != EOF)
        {
            dictionary_word = malloc((strlen(buffer2) + 1) * sizeof(char));
            if (dictionary_word == NULL)
            {
                fprintf(stderr, "main: unable to allocate memory for the read line");
                exit(EXIT_FAILURE);
            }

            //strtok(buffer2, "\n");
            strcpy(dictionary_word, buffer2);

            temp = edit_distance_dyn(correct_me_word, dictionary_word, strlen(correct_me_word), strlen(dictionary_word));

            if (min > temp)
            {
                cont = 0;
                min = temp;
                min_distance_words[cont] = dictionary_word;
                cont++;
            }

            else if (min == temp)
            {
                min_distance_words[cont] = dictionary_word;
                cont++;
            }
        }

        printf("\nWords with edit_distance_min from --> %s : \n", correct_me_word);
        printf("\nEdit_distance_min : %d\n", min);

        for (int i = 0; i < cont; i++)
        {
            printf(" %s\n", min_distance_words[i]);
        }

        printf("\n\n");

        correct_me_word = strtok(NULL, ".,: ");
    }
}

int main(int argc, char const *argv[])
{
    if (argc < 3)
    {
        printf("Usage: edit_distance_main <path_to_data_file>\n");
        exit(EXIT_FAILURE);
    }

    start_test(argv[1], argv[2]);

    return (EXIT_SUCCESS);
}