import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnergyCycleData } from 'app/shared/model/energy-cycle-data.model';
import { Principal } from 'app/core';
import { EnergyCycleDataService } from './energy-cycle-data.service';

@Component({
    selector: 'jhi-energy-cycle-data',
    templateUrl: './energy-cycle-data.component.html'
})
export class EnergyCycleDataComponent implements OnInit, OnDestroy {
    energyCycleData: IEnergyCycleData[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private energyCycleDataService: EnergyCycleDataService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.energyCycleDataService.query().subscribe(
            (res: HttpResponse<IEnergyCycleData[]>) => {
                this.energyCycleData = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnergyCycleData();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnergyCycleData) {
        return item.id;
    }

    registerChangeInEnergyCycleData() {
        this.eventSubscriber = this.eventManager.subscribe('energyCycleDataListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
