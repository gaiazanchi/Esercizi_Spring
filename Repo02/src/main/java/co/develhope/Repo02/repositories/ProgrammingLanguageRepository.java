package co.develhope.Repo02.repositories;

import co.develhope.Repo02.entities.ProgrammingLanguage;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "repo-prog-languages", collectionResourceDescription = @Description("Description"))
public interface ProgrammingLanguageRepository extends PagingAndSortingRepository<ProgrammingLanguage, Integer> {
}
