//
//  File.swift
//  
//
//  Created by 李伟 on 2024/10/27.
//

import Vapor

struct ResultResponse<T: Content>: Content {
    let code: Int
    let message: String
    let data: T?

    init(resultEnum: ResultEnum, data: T? = nil) {
        self.code = resultEnum.rawValue
        self.message = resultEnum.description
        self.data = data
    }
    
}
