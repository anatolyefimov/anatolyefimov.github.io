package com.nodepipes.core.domain.messaging.wrapper

import com.nodepipes.core.domain.messaging.message.Message

interface BasicNodeInput: NodeInput {

    fun getMessage(): Message

}