#include <bits/stdc++.h>

#define lli long long int
#define pb push_back
#define mod 1000000007
#define INF 1e18
#define SIZE 100001

using namespace std;

vector<lli> adj[SIZE];
bool visited[SIZE];
lli dist[SIZE], discover[SIZE], finish[SIZE];
lli t = 1;
stack<lli> st;

void dfs(lli s)
{
	visited[s] = 1;
	discover[s] = t++;
	lli i, v;
	for (i = 0; i < adj[s].size(); i++)
	{
		v = adj[s][i];
		if(!visited[v])
			dfs(v)
	}
	st.push(s);
	finish[s]=t++;
}

int main()
{
	lli u, v, i, j, k, N = 0;
	pair<lli,lli> current;

	ifstream iFile("bfs.in");
	string str, num;

	while (!iFile.eof())
	{
		getline(iFile, str);
		stringstream ss;
		ss << str;

		ss >> num;
		stringstream(num) >> u;

		while (!ss.eof())
		{
			ss >> num;
			if (stringstream(num) >> v)
				adj[u].pb(v);
		}
		N++;
		sort(adj[u].begin(), adj[u].end());
	}

	for (i = 1; i <= N; i++)
		dist[i] = INF;
	memset(visited,0,sizeof(visited));

	queue<lli> q;
	q.push(1);
	dist[i] = 0;
	visited[i] = 1;

	while (!q.empty())
	{
		u = q.front(); q.pop();
		for (i = 0; i < adj[u].size(); i++)
		{
			v = adj[u][i];
			if (!visited[v])
			{
				visited[v] = 1;
				dist[v] = dist[u] + 1;
				q.push(v);
			}
		}
	}

	multiset<pair<lli, lli>> s;
	for (i = 1; i <= N; i++)
		s.insert(make_pair(dist[i],i));
	
	memset(visited, 0, sizeof(visited));

	for (i = 1; i <= N; i++)cout << "bfs results: " << endl;

	while (!s.empty())
	{
		current = *s.begin(); s.erase(s.begin());
		i = current.second; j = current.first;
		if (J == INF)
			cout << i << " INF" << endl;
		else
			cout << i << " " << j << endl;
	}
	{
		if (visited[i])
			continue;
		dfs(i);
	}

	for (i = 1; i <= N; i++)
		s.insert(make_pair(discover[i], i))
	cout << "\n\ndfs results: " << endl;

	while (!s.empty())
	{
		current = *s.begin(); s.erase(s.begin());
		i = current.second; 
		cout << i << " " << discover[i] << " " << finish[i] << endl;
	}

	cout << "\n\ntopological sort results: " << endl;
	while (!st.empty())
	{
		i = st.top(); st.pop();
		cout << i << endl;
	}
}
