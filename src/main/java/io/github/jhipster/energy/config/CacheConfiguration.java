package io.github.jhipster.energy.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(io.github.jhipster.energy.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.EnergyContractedPowerRatingData.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.EnergyCycleData.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.EnergyVoltageData.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.EnergyTariffData.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.EnergyTimePeriodData.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.EnergyElectricityData.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.EnergyFuelData.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.EnergyNaturalGasData.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.EnergyVehicleBrandData.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.EnergyVehicleBrandData.class.getName() + ".models", jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.EnergyVehicleModelData.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.EnergyNRStateHistory.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.UserSAC.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.UserSAC.class.getName() + ".needNGRequests", jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.UserSAC.class.getName() + ".stateHistories", jcacheConfiguration);
            cm.createCache(io.github.jhipster.energy.domain.EnergyNeedNGRequest.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
