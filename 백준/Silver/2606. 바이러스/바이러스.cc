#include <iostream>
#include <queue>
#include <vector>

using namespace std;

vector<int> v[101];
bool visited[101];
queue<int> q;

int bfs(int start){
    int count=-1;
    q.push(start);
    visited[start]= true;
    
    while(!q.empty()){
        int x = q.front();
        q.pop();
        count++;
        
        for(int i=0; i<v[x].size(); i++){
            int y = v[x][i];
            if(visited[y]==false){
                q.push(y);
                visited[y]=true;
            }
        }
    }
    return count; //1번 컴터 팝하는거 뺴주
}

int main(){
    int computer,pair;
    int a,b;
    cin >> computer >> pair;
    
    for(int i=1; i<=pair; i++){
        cin >> a >> b;
        v[a].push_back(b);
        v[b].push_back(a);
    }
    
    cout << bfs(1);
}
