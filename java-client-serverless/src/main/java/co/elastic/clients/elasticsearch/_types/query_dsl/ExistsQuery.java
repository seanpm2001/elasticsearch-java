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

package co.elastic.clients.elasticsearch._types.query_dsl;

import co.elastic.clients.json.JsonpDeserializable;
import co.elastic.clients.json.JsonpDeserializer;
import co.elastic.clients.json.JsonpMapper;
import co.elastic.clients.json.ObjectBuilderDeserializer;
import co.elastic.clients.json.ObjectDeserializer;
import co.elastic.clients.util.ApiTypeHelper;
import co.elastic.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;
import java.lang.String;
import java.util.Objects;
import java.util.function.Function;
import javax.annotation.Nullable;

//----------------------------------------------------------------
//       THIS CODE IS GENERATED. MANUAL EDITS WILL BE LOST.
//----------------------------------------------------------------
//
// This code is generated from the Elasticsearch API specification
// at https://github.com/elastic/elasticsearch-specification
//
// Manual updates to this file will be lost when the code is
// re-generated.
//
// If you find a property that is missing or wrongly typed, please
// open an issue or a PR on the API specification repository.
//
//----------------------------------------------------------------

// typedef: _types.query_dsl.ExistsQuery

/**
 *
 * @see <a href="../../doc-files/api-spec.html#_types.query_dsl.ExistsQuery">API
 *      specification</a>
 */
@JsonpDeserializable
public class ExistsQuery extends QueryBase implements QueryVariant {
	private final String field;

	// ---------------------------------------------------------------------------------------------

	private ExistsQuery(Builder builder) {
		super(builder);

		this.field = ApiTypeHelper.requireNonNull(builder.field, this, "field");

	}

	public static ExistsQuery of(Function<Builder, ObjectBuilder<ExistsQuery>> fn) {
		return fn.apply(new Builder()).build();
	}

	/**
	 * Query variant kind.
	 */
	@Override
	public Query.Kind _queryKind() {
		return Query.Kind.Exists;
	}

	/**
	 * Required - Name of the field you wish to search.
	 * <p>
	 * API name: {@code field}
	 */
	public final String field() {
		return this.field;
	}

	protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

		super.serializeInternal(generator, mapper);
		generator.writeKey("field");
		generator.write(this.field);

	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Builder for {@link ExistsQuery}.
	 */

	public static class Builder extends QueryBase.AbstractBuilder<Builder> implements ObjectBuilder<ExistsQuery> {
		private String field;

		/**
		 * Required - Name of the field you wish to search.
		 * <p>
		 * API name: {@code field}
		 */
		public final Builder field(String value) {
			this.field = value;
			return this;
		}

		@Override
		protected Builder self() {
			return this;
		}

		/**
		 * Builds a {@link ExistsQuery}.
		 *
		 * @throws NullPointerException
		 *             if some of the required fields are null.
		 */
		public ExistsQuery build() {
			_checkSingleUse();

			return new ExistsQuery(this);
		}
	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Json deserializer for {@link ExistsQuery}
	 */
	public static final JsonpDeserializer<ExistsQuery> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
			ExistsQuery::setupExistsQueryDeserializer);

	protected static void setupExistsQueryDeserializer(ObjectDeserializer<ExistsQuery.Builder> op) {
		QueryBase.setupQueryBaseDeserializer(op);
		op.add(Builder::field, JsonpDeserializer.stringDeserializer(), "field");

	}

}