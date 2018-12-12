import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnergyContractedPowerRatingData } from 'app/shared/model/energy-contracted-power-rating-data.model';
import { Principal } from 'app/core';
import { EnergyContractedPowerRatingDataService } from './energy-contracted-power-rating-data.service';

@Component({
    selector: 'jhi-energy-contracted-power-rating-data',
    templateUrl: './energy-contracted-power-rating-data.component.html'
})
export class EnergyContractedPowerRatingDataComponent implements OnInit, OnDestroy {
    energyContractedPowerRatingData: IEnergyContractedPowerRatingData[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private energyContractedPowerRatingDataService: EnergyContractedPowerRatingDataService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.energyContractedPowerRatingDataService.query().subscribe(
            (res: HttpResponse<IEnergyContractedPowerRatingData[]>) => {
                this.energyContractedPowerRatingData = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnergyContractedPowerRatingData();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnergyContractedPowerRatingData) {
        return item.id;
    }

    registerChangeInEnergyContractedPowerRatingData() {
        this.eventSubscriber = this.eventManager.subscribe('energyContractedPowerRatingDataListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
