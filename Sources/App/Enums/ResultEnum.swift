//
//  File.swift
//  
//
//  Created by 李伟 on 2024/10/27.
//

import Foundation



// ResultEnum 提供了 API 响应状态码和描述
enum ResultEnum: Int, CustomStringConvertible {
    case success = 200
    case error = 400
    case userNotFound = 404
    case invalidInput = 422

    var description: String {
        switch self {
        case .success:
            return ResultConstants.SUCCESS
        case .error:
            return ResultConstants.ERROR
        case .userNotFound:
            return ResultConstants.USER_NOT_FOUND
        case .invalidInput:
            return ResultConstants.INVALID_INPUT
        }
    }
}
