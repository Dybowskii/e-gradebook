# 1. Użyj oficjalnego obrazu node z npm
FROM node:18-alpine as build

# 2. Ustaw katalog roboczy
WORKDIR /app

# 3. Skopiuj pliki
COPY . .

# 4. Zainstaluj zależności i zbuduj aplikację
RUN npm install
RUN npm run build

# 5. Użyj nginx do serwowania statycznych plików
FROM nginx:stable-alpine

# 6. Skopiuj zbudowaną aplikację do katalogu nginx
COPY --from=build /app/build /usr/share/nginx/html

# 7. Opcjonalnie: nadpisz domyślny config nginx (jeśli masz własny)
# COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
