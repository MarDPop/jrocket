clear 
clc

M = csvread('World.csv');
[m, n] = size(M);
long = M(:,1);
lat = M(:,2);
value = M(:,3);

[xq,yq] = meshgrid(linspace(long(1),long(end),300),linspace(lat(1),lat(end),300));
z3 = griddata(long,lat,value,xq,yq,'natural');
contourf(z3)
pause;
figure
scatter(long,lat);

figure
hold on
M = csvread('data.csv');
long = M(:,1);
lat = M(:,2);
scatter(long,lat,'r');
M = csvread('centroids.csv');
long = M(:,1);
lat = M(:,2);
scatter(long,lat,'b');