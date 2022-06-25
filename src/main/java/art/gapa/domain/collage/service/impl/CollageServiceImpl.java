package art.gapa.domain.collage.service.impl;

import art.gapa.domain.collage.CollageInstance;
import art.gapa.domain.collage.CollageType;
import art.gapa.domain.collage.repository.CollageInstanceRepository;
import art.gapa.domain.collage.service.CollageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author JoverZhang
 */
@Service
@RequiredArgsConstructor
public class CollageServiceImpl implements CollageService {

    private final CollageInstanceRepository instanceRepository;

    @Override
    public CollageInstance createInstance(CollageType type, long userId) {
        CollageInstance instance = CollageInstance.create(type, userId);
        instanceRepository.save(instance);
        return instance;
    }

}
