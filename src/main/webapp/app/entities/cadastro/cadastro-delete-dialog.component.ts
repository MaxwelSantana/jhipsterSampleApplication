import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Cadastro } from './cadastro.model';
import { CadastroPopupService } from './cadastro-popup.service';
import { CadastroService } from './cadastro.service';

@Component({
    selector: 'jhi-cadastro-delete-dialog',
    templateUrl: './cadastro-delete-dialog.component.html'
})
export class CadastroDeleteDialogComponent {

    cadastro: Cadastro;

    constructor(
        private cadastroService: CadastroService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cadastroService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'cadastroListModification',
                content: 'Deleted an cadastro'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cadastro-delete-popup',
    template: ''
})
export class CadastroDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private cadastroPopupService: CadastroPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.cadastroPopupService
                .open(CadastroDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
