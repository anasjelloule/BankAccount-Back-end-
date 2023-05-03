package ma.voltify.bankweb;

import java.util.List;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;

@Component
public class GraphqlexceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        return new GraphQLError() {

            @Override
            public ErrorClassification getErrorType() {
                return null;
            }

            @Override
            public List<SourceLocation> getLocations() {
                return null;
            }

            @Override
            public String getMessage() {
                return ex.getMessage();
            }

        };
    }

}
