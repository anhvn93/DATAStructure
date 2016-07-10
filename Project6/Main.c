
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
bool dfsearch(int, int);

int m,mini,n;
bool fin[14];
struct CLASS {
    char name[8];
    int time,preNumber;
    int prereq[13];
} subject[15];

bool permitPass(int num) {
    int i;
    for(i=0; i<subject[num].preNumber && fin[subject[num].prereq[i]]; i++);
    if(i == subject[num].preNumber) return true;
    return false;
}

bool selectAnddfsearch(int total, int enums, int from, int queu[], int result[], int level, int semester) {
    int i;
    if(total-from < enums) return false;
    if(from == total && enums == 0) {
        for(i=0; i<m; i++)
        	fin[result[i]] = true;
        dfsearch(level+m, semester+1);
        for(i=0; i<m; i++)
        	fin[result[i]] = false;
        return true;
    }
    for(i=from; i<total; i++) {
        result[m-enums] = queu[i];
        selectAnddfsearch(total, enums-1, i+1, queu, result, level, semester);
    }
    return true;
}

bool dfsearch(int level, int semester) {
    if(semester-1 >= mini) return false;
    if(level == n) {
        if(semester-1 < mini)
        	mini = semester-1;
        return true;
    }
    int i,tn=0,queu[15];
    for(i=0; i<n; i++)
    	if(!fin[i] && permitPass(i) && (subject[i].time & (1<<semester%2)) != 0)
    		queu[tn++] = i;
    if(tn <= m) {
        for(i=0; i<tn; i++)
        	fin[queu[i]] = true;
        dfsearch(level+tn,semester+1);
        for(i=0; i<tn; i++)
        	fin[queu[i]] = false;
    }
    else {
        int tmp[15];
        selectAnddfsearch(tn,m,0,queu,tmp,level,semester);
    }
    return false;
}

int main() {
    while(scanf("%d%d", &n, &m) == 2 && !(n == -1 && m == -1)) {
        int i,j,k,pi,t;
        for(i=0; i<n; i++) {
            scanf("%s", &subject[i].name);
            subject[i].time = subject[i].preNumber = 0;
        }
        for(i=0; i<n; i++) {
            char s[10];
            scanf("%s", s);
            for(j=0; j<n && strcmp(s, subject[j].name) != 0; j++);
            scanf("%s", s);
            if(s[0] == 'S')
            	subject[j].time = 1;
            else if(s[0] == 'B')
            	subject[j].time = 3;
            else
            	subject[j].time = 2;
            scanf("%d", &t);
            for(k=0; k<t; k++) {
                scanf("%s", s);
                for(pi=0; pi<n && strcmp(s, subject[pi].name) != 0; pi++);
                subject[j].prereq[subject[j].preNumber++] = pi;
            }
        }
        mini = 100000;
        memset(fin, 0, sizeof(fin));
        dfsearch(0,1);
        printf("Minimum number of semesters required to graduate is %d.\n", mini);
    }
    return 0;
}
