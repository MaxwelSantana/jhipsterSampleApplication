package io.github.jhipster.application.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(io.github.jhipster.application.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Cliente.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Funcionario.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Coletor.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.TipoContagem.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.StatusContagem.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Inventario.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Transmissao.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Secao.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.MotivoAlteracao.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Contagem.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.LogAlteracaoContagem.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Cadastro.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Mapeamento.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.StatusInventario.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.LogStatusInventario.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.CampoCustomizavelColeta.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.CampoCustomizavelCadastro.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.TipoConfiguracao.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.CodigoCustomizavel.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.ConfiguracaoColetor.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Configuracao.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Cliente.class.getName() + ".inventarios", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
