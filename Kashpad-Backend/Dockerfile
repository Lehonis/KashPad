FROM eclipse-temurin:21-jdk-jammy AS extract_artifacts
WORKDIR /build

COPY target/*.jar target/Kashpad_BackEnd.jar
RUN java -Djarmode=layertools -jar target/Kashpad_BackEnd.jar extract --destination target/extracted

FROM eclipse-temurin:21-jre-jammy AS final
WORKDIR /app

ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser

COPY --from=extract_artifacts --chown=appuser:appuser /build/target/extracted/dependencies/ ./
COPY --from=extract_artifacts --chown=appuser:appuser /build/target/extracted/spring-boot-loader/ ./
COPY --from=extract_artifacts --chown=appuser:appuser /build/target/extracted/snapshot-dependencies/ ./
COPY --from=extract_artifacts --chown=appuser:appuser /build/target/extracted/application/ ./

USER appuser

EXPOSE 8080
ENTRYPOINT [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]