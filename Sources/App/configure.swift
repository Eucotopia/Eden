import NIOSSL
import Fluent
import Vapor
import FluentMySQLDriver

// configures your application
public func configure(_ app: Application) async throws {
    // uncomment to serve files from /Public folder
    // app.middleware.use(FileMiddleware(publicDirectory: app.directory.publicDirectory))
    var tls = TLSConfiguration.makeClientConfiguration()
    tls.certificateVerification = .none

    app.databases.use(.mysql(
        hostname: "127.0.0.1",
        port: MySQLConfiguration.ianaPortNumber,
        username: "root",
        password: "Lw001208...",
        database: "blog",
        tlsConfiguration: tls
    ), as: .mysql)

    // 添加迁移
    app.migrations.add(UserModelMigration())

    do {
        try await app.autoMigrate()
        app.logger.info("Migration completed successfully.")
    } catch {
        app.logger.error("Migration failed: \(error.localizedDescription)")
    }

    app.logger.logLevel = .debug

    // register routes
    try routes(app)
}
