FROM node:20-alpine AS build
WORKDIR /app
COPY package.json package-lock.json* ./
RUN npm ci
COPY . .
RUN npx ng build --configuration=production

FROM nginx:alpine
COPY --from=build /app/dist/petsitting-hub/browser /usr/share/nginx/html
COPY .nginx/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
HEALTHCHECK --interval=30s --timeout=5s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://127.0.0.1:80/health || exit 1
CMD ["nginx", "-g", "daemon off;"]
