import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnergyElectricityData } from 'app/shared/model/energy-electricity-data.model';
import { EnergyElectricityDataService } from './energy-electricity-data.service';

@Component({
    selector: 'jhi-energy-electricity-data-delete-dialog',
    templateUrl: './energy-electricity-data-delete-dialog.component.html'
})
export class EnergyElectricityDataDeleteDialogComponent {
    energyElectricityData: IEnergyElectricityData;

    constructor(
        private energyElectricityDataService: EnergyElectricityDataService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.energyElectricityDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'energyElectricityDataListModification',
                content: 'Deleted an energyElectricityData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-energy-electricity-data-delete-popup',
    template: ''
})
export class EnergyElectricityDataDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyElectricityData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnergyElectricityDataDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.energyElectricityData = energyElectricityData;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
