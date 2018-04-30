import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TipoContagem } from './tipo-contagem.model';
import { TipoContagemPopupService } from './tipo-contagem-popup.service';
import { TipoContagemService } from './tipo-contagem.service';

@Component({
    selector: 'jhi-tipo-contagem-delete-dialog',
    templateUrl: './tipo-contagem-delete-dialog.component.html'
})
export class TipoContagemDeleteDialogComponent {

    tipoContagem: TipoContagem;

    constructor(
        private tipoContagemService: TipoContagemService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoContagemService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tipoContagemListModification',
                content: 'Deleted an tipoContagem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-contagem-delete-popup',
    template: ''
})
export class TipoContagemDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tipoContagemPopupService: TipoContagemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tipoContagemPopupService
                .open(TipoContagemDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
