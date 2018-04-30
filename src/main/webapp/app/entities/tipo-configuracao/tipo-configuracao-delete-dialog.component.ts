import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TipoConfiguracao } from './tipo-configuracao.model';
import { TipoConfiguracaoPopupService } from './tipo-configuracao-popup.service';
import { TipoConfiguracaoService } from './tipo-configuracao.service';

@Component({
    selector: 'jhi-tipo-configuracao-delete-dialog',
    templateUrl: './tipo-configuracao-delete-dialog.component.html'
})
export class TipoConfiguracaoDeleteDialogComponent {

    tipoConfiguracao: TipoConfiguracao;

    constructor(
        private tipoConfiguracaoService: TipoConfiguracaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoConfiguracaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tipoConfiguracaoListModification',
                content: 'Deleted an tipoConfiguracao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-configuracao-delete-popup',
    template: ''
})
export class TipoConfiguracaoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tipoConfiguracaoPopupService: TipoConfiguracaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tipoConfiguracaoPopupService
                .open(TipoConfiguracaoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
