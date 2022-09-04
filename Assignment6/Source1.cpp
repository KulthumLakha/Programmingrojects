#include <iostream>
#include <iterator>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

void displayAdjList(vector<int> adj_list[], int v) 
{
    for (int i = 0; i < v; i++) 
    {
        cout << i << "--->";
        vector<int> ::iterator it;
        for (it = adj_list[i].begin(); it != adj_list[i].end(); ++it) 
        {
            cout << *it << " ";
        }
        cout << endl;
    }
}

int main(int argc, char* argv[])
{
    vector<int> A;
    string str, num;
    int u, v;
    int N = 0;

    if (argc != 2)
    {
        cout << "Provide an input file as argument";
    }
    else
    {
        FILE* file = fopen(argv[1], "r");
        if (file == 0)
        {
            cout << "ERROR: file does not exist" << endl;
            return -1;
        }
        else
        {
            while (!file.eof())
            {
                getline(file, str);
                stringstream ss;
                ss << str;

                ss >> num;
                stringstream(num) >> u;

                while (!ss.eof())
                {
                    ss >> num;
                    if (stringstream(num) >> v)
                        A[u].push_back(v);
                }
                N++;
                sort(A[u].begin(), A[u].end());
            }
            fclose(file);
        }
    }

    displayAdjList(A, N);
    return 0;
}
