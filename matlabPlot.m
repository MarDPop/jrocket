clear 
clc

M = csvread('World.csv');
[m, n] = size(M);
long = M(:,1);
lat = M(:,2);
value = M(:,3);

[xq,yq] = meshgrid(linspace(long(1),long(end),300),linspace(lat(1),lat(end),300));
z3 = griddata(long,lat,value,xq,yq,'natural');

for i = 1:m
    for j = 1:n
        
    end
end

figure
contourf(xq,yq,z3,[1e-11 2.5e-11 5e-11 1e-10 2e-10 4e-10 8e-10 1.6e-9 3.2e-9 6.4e-9 1.28e-9 2.56e-9 ])
pause;

% figure
% scatter(long,lat);

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