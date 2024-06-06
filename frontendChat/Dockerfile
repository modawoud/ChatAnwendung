
FROM node:latest as build

LABEL authors="admin"

RUN mkdir -p /app
WORKDIR /app
COPY . /app/
COPY package.json /app/
RUN npm install
RUN npm run build

FROM nginx:latest

COPY --from=build /app/dist/frontendChat /usr/share/nginx/html

EXPOSE 80
