import sys
sys.setrecursionlimit(99999999)
n, m = map(int, sys.stdin.readline().split())
road = []
for i in range (n):
    line = list(map(int, sys.stdin.readline().split()))
    road.append(line)

visited = [[-1 for _ in range (m)] for _ in range (n)]
dx = [1, 0, -1, 0] 
dy = [0, -1, 0, 1]

def dfs(x, y):
    if x == n-1 and y == m-1:
        return 1

    if visited[x][y] != -1:
        return visited[x][y]

    visited[x][y] = 0

    for i in range (4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < n and 0 <= ny < m:
            if road[nx][ny] < road[x][y]:
                visited[x][y] += dfs(nx, ny)

    return visited[x][y]

print(dfs(0,0))

