import Fluent
import Vapor

// Define the routes for the application
func routes(_ app: Application) throws {

    let userController = UserController()

    // 用户相关的路由组
    app.group("users") { users in
        // User routes
        app.post("register", use: userController.register)
        app.post("users", use: userController.create)
        app.get("users", use: userController.getAll)
        app.get("users", ":userID", use: userController.get)
        app.put("users", ":userID", use: userController.update)
        app.delete("users", ":userID", use: userController.delete)
    }

}
