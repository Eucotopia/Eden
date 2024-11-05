@testable import App
import XCTVapor
import Fluent

final class AppTests: XCTestCase {
    var app: Application!

    func testHelloWorld() async throws {
        try await self.app.test(.GET, "hello", afterResponse: { res async in
            XCTAssertEqual(res.status, .ok)
            XCTAssertEqual(res.body.string, "Hello, world!")
        })
    }
}
