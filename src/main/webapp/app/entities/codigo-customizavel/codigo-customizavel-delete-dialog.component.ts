import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CodigoCustomizavel } from './codigo-customizavel.model';
import { CodigoCustomizavelPopupService } from './codigo-customizavel-popup.service';
import { CodigoCustomizavelService } from './codigo-customizavel.service';

@Component({
    selector: 'jhi-codigo-customizavel-delete-dialog',
    templateUrl: './codigo-customizavel-delete-dialog.component.html'
})
export class CodigoCustomizavelDeleteDialogComponent {

    codigoCustomizavel: CodigoCustomizavel;

    constructor(
        private codigoCustomizavelService: CodigoCustomizavelService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.codigoCustomizavelService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'codigoCustomizavelListModification',
                content: 'Deleted an codigoCustomizavel'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-codigo-customizavel-delete-popup',
    template: ''
})
export class CodigoCustomizavelDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private codigoCustomizavelPopupService: CodigoCustomizavelPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.codigoCustomizavelPopupService
                .open(CodigoCustomizavelDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
