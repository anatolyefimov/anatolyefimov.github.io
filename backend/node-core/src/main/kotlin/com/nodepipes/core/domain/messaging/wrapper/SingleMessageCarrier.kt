package com.nodepipes.core.domain.messaging.wrapper

import com.nodepipes.core.domain.messaging.message.Message

interface SingleMessageCarrier: MessageCarrier {

    fun getMessage(): Message

}