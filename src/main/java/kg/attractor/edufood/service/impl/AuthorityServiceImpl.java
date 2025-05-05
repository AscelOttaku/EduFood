package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.AuthorityDto;
import kg.attractor.edufood.mapper.AuthorityMapper;
import kg.attractor.edufood.mapper.UserMapper;
import kg.attractor.edufood.repository.AuthorityRepository;
import kg.attractor.edufood.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityMapper authorityMapper;
    private final AuthorityRepository authorityRepository;

    @Override
    public AuthorityDto getAuthorityById(Long id) {
        return authorityRepository.findById(id)
                .map(authorityMapper::mapToDto)
                .orElseThrow(NoSuchElementException::new);

    }

}
