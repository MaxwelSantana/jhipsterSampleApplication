import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CampoCustomizavelColeta } from './campo-customizavel-coleta.model';
import { CampoCustomizavelColetaPopupService } from './campo-customizavel-coleta-popup.service';
import { CampoCustomizavelColetaService } from './campo-customizavel-coleta.service';

@Component({
    selector: 'jhi-campo-customizavel-coleta-delete-dialog',
    templateUrl: './campo-customizavel-coleta-delete-dialog.component.html'
})
export class CampoCustomizavelColetaDeleteDialogComponent {

    campoCustomizavelColeta: CampoCustomizavelColeta;

    constructor(
        private campoCustomizavelColetaService: CampoCustomizavelColetaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.campoCustomizavelColetaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'campoCustomizavelColetaListModification',
                content: 'Deleted an campoCustomizavelColeta'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-campo-customizavel-coleta-delete-popup',
    template: ''
})
export class CampoCustomizavelColetaDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private campoCustomizavelColetaPopupService: CampoCustomizavelColetaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.campoCustomizavelColetaPopupService
                .open(CampoCustomizavelColetaDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
