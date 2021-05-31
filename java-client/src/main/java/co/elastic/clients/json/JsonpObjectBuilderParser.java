/*
 * Licensed to Elasticsearch B.V. under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch B.V. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package co.elastic.clients.json;

import co.elastic.clients.util.ObjectBuilder;

import jakarta.json.stream.JsonParser;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * An object parser based on an {@link ObjectBuilder}.
 */
public class JsonpObjectBuilderParser<T> extends JsonpValueParser<T> {

    private final JsonpValueParser<? extends ObjectBuilder<T>> builderParser;

    public JsonpObjectBuilderParser(JsonpValueParser<? extends ObjectBuilder<T>> builderParser) {
        super(builderParser.acceptedEvents());
        this.builderParser = builderParser;
    }

    @Override
    public T parse(JsonParser parser, JsonpMapper mapper, JsonParser.Event event) {
        ObjectBuilder<T> builder = builderParser.parse(parser, mapper, event);
        return builder.build();
    }

    public static <T, B extends ObjectBuilder<T>> JsonpValueParser<T> createForObject(
        Supplier<B> ctor,
        Consumer<DelegatingJsonpValueParser<B>> configurer
    ) {
        JsonpObjectParser<B> op = new JsonpObjectParser<>(ctor);
        configurer.accept(op);
        return new JsonpObjectBuilderParser<>(op);
    }

    public static <T, B extends ObjectBuilder<T>> JsonpValueParser<T> createForValue(
        Supplier<B> ctor,
        Consumer<DelegatingJsonpValueParser<B>> configurer
    ) {
        JsonpValueBodyParser<B> op = new JsonpValueBodyParser<>(ctor);
        configurer.accept(op);
        return new JsonpObjectBuilderParser<>(op);
    }
}
