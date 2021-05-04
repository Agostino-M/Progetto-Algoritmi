#ifndef EDIT_DISTANCE_H
#define EDIT_DISTANCE_H
#define MIN(X, Y) (((X) < (Y)) ? (X) : (Y))
#define MAX_COL 1000

//Given two strings s1 and s2 and Find minimum number of edits (operations)
//required to convert ‘s1’ into ‘s2’.
int edit_distance(char *s1, char *s2, int m, int n);

//Given two strings s1 and s2 and Find minimum number of edits (operations)
//required to convert ‘s1’ into ‘s2’. Dynamic version
int edit_distance_dyn(char *s1, char *s2, int m, int n);

#endif /* EDIT_DISTANCE_H */
